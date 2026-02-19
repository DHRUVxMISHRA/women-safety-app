import requests
from fastapi import HTTPException
from app.core.config import GOOGLE_API_KEY
 # call direction api

DIRECTIONS_URL = "https://maps.googleapis.com/maps/api/directions/json"

def get_routes(
    origin: str,
    destination: str
):
    params = {
        "origin": origin,
        "destination": destination,
        "alternatives": "true",
        # "departure_time": "now",#
        # "traffic_model": "best_guess",
        "key": GOOGLE_API_KEY
    }

    response = requests.get(DIRECTIONS_URL, params=params)

    if response.status_code != 200:
        raise HTTPException(status_code=500, detail="Google API error")

    data = response.json()

    if data["status"] != "OK":
        raise HTTPException(status_code=400, detail=data["status"])

    routes_data = []

    for route in data["routes"]:
        routes_data.append({
            "distance": route["legs"][0]["distance"]["text"],
            "duration": route["legs"][0]["duration"]["text"],
            "polyline": route["overview_polyline"]["points"]
        })
    return routes_data # list