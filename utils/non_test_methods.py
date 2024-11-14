import csv, random
from datetime import datetime, timedelta
from urllib.parse import quote_plus

def open_csv_file(filepath = str):
    with open(filepath, "r") as file:
        reader = csv.DictReader(file)
        return list(reader)


def generateFlightsDates():
    dates_list = {}
    dates_list["depart_date"] = quote_plus((datetime.now() + timedelta(days= random.randint(3,10) ) ).strftime("%m/%d/%Y"))
    dates_list["return_date"] = quote_plus((datetime.now() + timedelta(days= random.randint(15,30) ) ).strftime("%m/%d/%Y"))

    return dates_list

def generateRandomCardNumber():
    return random.randrange(10**15,10**16)

def processCancelRequestBody(ids_list= list, names_list= list):

    cgi_part = ".cgifields="+"&.cgifields=".join(names_list)
    static_part = "&removeFlights.x=59&removeFlights.y=15"
    flights_part = "flightID="+"&flightID=".join(ids_list)
    todelete_part = f"{names_list[random.randrange(0, len(ids_list))]}=on"
    result = f"{todelete_part}&{flights_part}&{cgi_part}&{static_part}"

    return result