from locust import task, SequentialTaskSet, FastHttpUser, HttpUser, constant_pacing, constant_throughput ,events
from config.config import cfg, logger
from utils.assertion import check_http_response 
from utils.non_test_methods import open_csv_file
import sys, re , random



class PurchaseFlightTicket(SequentialTaskSet): # класс с задачами (содержит основной сценарий)
    
    test_users_csv_filepath = './test_data/test_users.csv'
    
    def on_start(self):
        @task
        def uc_00_getHomePage(self) -> None:
            self.test_users_data = open_csv_file(self.test_users_csv_filepath)
            logger.info(f"Test data for users is: {self.test_users_csv_filepath}")

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
            r01_00_headers = {
                'Content-Type':'application/x-www-form-urlencoded'
            }

            self.user_data_row = random.choice(self.test_users_data)
            logger.info(self.user_data_row)

            self.username= self.user_data_row ["Login"]
            self.password= self.user_data_row ["Password"]
            logger.info(f"choesn username: {self.username} / password: {self.password} ")

            r01_00_body = f"userSession={self.user_session}&username={self.username}&password={self.password}&login.x=29&login.y=9&JSFormSubmit=off"

            with self.client.post(
                '/cgi-bin/login.pl',
                name = 'req_01_0_LoginAction_/cgi-bin/login.pl',
                headers =  r01_00_headers,
                data = r01_00_body,
                # debug_stream = sys.stderr,
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
                debug_stream = sys.stderr
                
            )
        

        uc_00_getHomePage(self)
        uc_01_LoginAction(self)   
        
    @task
    def my_dummy_task(self):
        print('WHAT')


        
           

class WebToursBaseUserClass(FastHttpUser): # юзер-класс, принимающий в себя основные параметры теста
    wait_time = constant_pacing(cfg.pacing)
    host = cfg.url

    logger.info(f'WebToursBaseUserClass started. Host:{host}')

    tasks = [PurchaseFlightTicket]