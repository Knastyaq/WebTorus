from locust import task, SequentialTaskSet, FastHttpUser, HttpUser, constant_pacing, constant_throughput ,events
from config.config import cfg, logger
from utils.assertion import check_http_response 
from utils.non_test_methods import open_csv_file , generateFlightsDates
import sys, re , random
from urllib.parse import quote_plus , quote



class PurchaseFlightTicket(SequentialTaskSet): # класс с задачами (содержит основной сценарий)
    
    test_users_csv_filepath = './test_data/test_users.csv'
    test_flights_data_csv_filepath = './test_data/Cart_data.csv'

    test_users_data = open_csv_file(test_users_csv_filepath)
    test_flights_data = open_csv_file(test_flights_data_csv_filepath)
    

    post_request_content_type_header = {
            'Content-Type':'application/x-www-form-urlencoded'
    }
    
    
    def on_start(self):
        @task
        def uc_00_getHomePage(self) -> None:
            
            logger.info(f"Test data for users is: {self.test_users_data}")

            r00_01_headers = {
                'sec-fetch-mode': 'navigate'
            }
            self.client.get(
                '/WebTours/',
                name ="req_00_0_getHomePage_/WebTours/",
                allow_redirects = False,
                #debug_stream = sys.stderr
                
            )

            self.client.get(
                '/webtours/header.html',
                name ="req_00_1_getHtml_/webtours/header.html",
                allow_redirects = False,
                #debug_stream = sys.stderr
                
            )

            r00_2_url_param_signOff = 'true'

            self.client.get(
                f'/cgi-bin/welcome.pl?signOff={r00_2_url_param_signOff}',
                name ="req_00_2_getHomePage_cgi-bin/welcome.pl?signOff=true",
                allow_redirects = False,
                #debug_stream = sys.stderr
                
            )

            with self.client.get(
                f'/cgi-bin/nav.pl?in=home',
                name ="req_00_3_getHomePage_cgi-bin/nav.pl?in=home",
                allow_redirects = False,
                catch_response = True
                #debug_stream = sys.stderr
                
            ) as req00_3_response:
                check_http_response(req00_3_response, 'name="userSession"')
                # print(f"\n___\n req00_3_response.text: {req00_3_response.text}\n___\n")
            
            self.user_session = re.search(r"name=\"userSession\" value=\"(.*)\"\/>",req00_3_response.text ).group(1)

            logger.info(f" USER_SESSION PARAMETER: {self.user_session}")    

            self.client.get(
                '/WebTours/home.html',
                name ="req_00_4_getHomePage_WebTours/home.html",
                allow_redirects = False,
                #debug_stream = sys.stderr
                
            )
            
        @task
        def uc_01_LoginAction(self) -> None:
            

            self.user_data_row = random.choice(self.test_users_data)
            logger.info(self.user_data_row)

            self.username= self.user_data_row ["Login"]
            self.password= self.user_data_row ["Password"]
            logger.info(f"choesn username: {self.username} / password: {self.password} ")

            r01_00_body = f"userSession={self.user_session}&username={self.username}&password={self.password}&login.x=29&login.y=9&JSFormSubmit=off"

            with self.client.post(
                '/cgi-bin/login.pl',
                name = 'req_01_0_LoginAction_/cgi-bin/login.pl',
                headers = self.post_request_content_type_header,
                data = r01_00_body,
                 #debug_stream = sys.stderr,
                catch_response = True

            ) as r01_00_response: 
                check_http_response(r01_00_response, "User password was correct")


            self.client.get(
                '/cgi-bin/nav.pl?page=menu&in=home',
                name ="req_01_1_LoginAction_/cgi-bin/nav.pl?page=menu&in=home",
                allow_redirects = False,
                # debug_stream = sys.stderr
                
            )
        
            r01_2_url_param_intro = 'true'
        
            self.client.get(
                f'/cgi-bin/login.pl?intro={r01_2_url_param_intro}',
                name ="req_01_2_LoginAction_/cgi-bin/login.pl?intro=true",
                allow_redirects = False,
                #debug_stream = sys.stderr
                
            )
        

        uc_00_getHomePage(self)
        uc_01_LoginAction(self)   
        
    @task
    def uc02_OpenFlightsTab(self):
        self.client.get(
            f'/cgi-bin/welcome.pl?page=search',
            name ="req_02_0_OpenFlightsTab_cgi-bin/welcome.pl?page=search",
            allow_redirects = False,
            #debug_stream = sys.stderr
        )

        self.client.get(
            f'/cgi-bin/nav.pl?page=menu&in=flights',
            name ="req_02_1_OpenFlightsTab_cgi-bin/nav.pl?page=menu&in=flights",
            allow_redirects = False,
            #debug_stream = sys.stderr
        )

        self.client.get(
            f'/cgi-bin/reservations.pl?page=welcome',
            name ="req_02_2_OpenFlightsTab_cgi-bin/reservations.pl?page=welcome",
            allow_redirects = False,
            #debug_stream = sys.stderr
        )

    @task
    def uc03_FindFlight_Inputparams(self):
        self.flights_data_row = random.choice(self.test_flights_data)

        depart= self.user_data_row ["depart"]
        arrive= self.user_data_row ["arrive"]
        self.seatPref= self.flights_data_row ["seatPref"]
        self.seatType= self.flights_data_row ["seatType"]
        self.Login = self.user_data_row ["Login"]
        self.Password = self.user_data_row ["Password"]
        self.address1 = self.user_data_row ["address1"]
        self.address2 = self.user_data_row ["address2"]
        self.pass1 = self.user_data_row ["pass1"]
        self.creditCard = self.flights_data_row ["creditCard"]
        self.expDate = self.flights_data_row ["expDate"]

        dates_dict = generateFlightsDates()

        r03_00_body = f"advanceDiscount=0&depart={depart}&departDate={dates_dict["depart_date"]}&arrive={arrive}&returnDate={dates_dict["return_date"]}&numPassengers=1&seatPref={self.seatPref}&seatType={self.seatType}&findFlights.x=52&findFlights.y=11&.cgifields=roundtrip&.cgifields=seatType&.cgifields=seatPref"
        logger.info(f"us03 request body : {r03_00_body}")

        with self.client.post(
            '/cgi-bin/reservations.pl',
            name = 'req_03_0_FindFlight_Inputparams_/cgi-bin/reservations.pl',
            headers = self.post_request_content_type_header,
            data = r03_00_body,
            #debug_stream = sys.stderr,
            catch_response = True

        ) as r_03_0response:
            check_http_response(r_03_0response, "Flight departing from")
            self.outboundFlight = re.search(r'name=\"outboundFlight\" value=\"(.*)\" checked=\"checked\"',r_03_0response.text ).group(1)
            logger.info(f"r03___self.outboundFlight:{self.outboundFlight}")

    @task
    def uc04_ChooseFlightOption(self):

        r04_00_body = f"outboundFlight={quote_plus(self.outboundFlight)}&numPassengers=1&advanceDiscount=0&seatType={self.seatType}&seatPref={self.seatPref}&reserveFlights.x=46&reserveFlights.y=11"
        logger.info(f"us04 request body : {r04_00_body}")

        with self.client.post(
            '/cgi-bin/reservations.pl',
            name = 'req_04_0_ChooseFlightOption_/cgi-bin/reservations.pl',
            headers = self.post_request_content_type_header,
            data = r04_00_body,
            #debug_stream = sys.stderr,
            catch_response = True

        ) as r_04_0response:
            check_http_response(r_04_0response, "Total for 1 ticket(s) is =")


    @task
    def uc05_ConfirmFlightBooking(self):



        r05_00_body = f"firstName={self.Login}&lastName={self.Password}&address1={self.address1}&address2={quote(self.address2)}&pass1={self.pass1}&creditCard={generateFlightsDates()}&expDate={self.expDate}&saveCC=on&oldCCOption=on&numPassengers=1&seatType={self.seatType}&seatPref={self.seatPref}&outboundFlight={quote_plus(self.outboundFlight)}&advanceDiscount=0&returnFlight=&JSFormSubmit=off&buyFlights.x=81&buyFlights.y=13&.cgifields=saveCC"
        logger.info(f"us05 request body : {r05_00_body}")

        with self.client.post(
            '/cgi-bin/reservations.pl',
            name = 'req_05_0_ConfirmFlightBooking_/cgi-bin/reservations.pl',
            headers = self.post_request_content_type_header,
            data = r05_00_body,
            #debug_stream = sys.stderr,
            catch_response = True

        ) as r_05_0response:
            
            check_http_response(r_05_0response, "!-- Flight reserved --")





class WebToursBaseUserClass(FastHttpUser): # юзер-класс, принимающий в себя основные параметры теста
    wait_time = constant_pacing(cfg.webtours_base.pacing)
    host = cfg.url

    logger.info(f'WebToursBaseUserClass started. Host:{host}')

    tasks = [PurchaseFlightTicket]