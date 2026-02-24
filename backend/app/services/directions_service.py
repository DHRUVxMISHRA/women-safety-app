# import httpx
# from fastapi import HTTPException
# from app.core.config import GOOGLE_API_KEY
#  # call direction api

# DIRECTIONS_URL = "https://maps.googleapis.com/maps/api/directions/json"

# async def get_routes(
#     origin: str,
#     destination: str
# ):
#     params = {
#         "origin": origin,
#         "destination": destination,
#         "alternatives": "true",
#         # "departure_time": "now",#
#         # "traffic_model": "best_guess",
#         "key": GOOGLE_API_KEY
#     }

#     async with httpx.AsyncClient() as client:
#         response = await client.get(DIRECTIONS_URL, params=params)

#     if response.status_code != 200:
#         raise HTTPException(status_code=500, detail="Google API error")

#     data = response.json()

#     if data["status"] != "OK":
#         raise HTTPException(status_code=400, detail=data["status"])

#     routes_data = []

#     for route in data["routes"]:
#         routes_data.append({
#             "distance": route["legs"][0]["distance"]["text"],
#             "duration": route["legs"][0]["duration"]["text"],
#             "polyline": route["overview_polyline"]["points"]
#         })
#     return routes_data # list

# import httpx
# import os

# GOOGLE_API_KEY = os.getenv("GOOGLE_MAPS_API_KEY")


# async def fetch_routes(origin, destination):
#     """
#     Calls Google Directions API and returns routes.
#     """

#     url = "https://maps.googleapis.com/maps/api/directions/json"

#     params = {
#         "origin": f"{origin[0]},{origin[1]}",
#         "destination": f"{destination[0]},{destination[1]}",
#         "alternatives": "true",   # IMPORTANT → multiple routes
#         "key": GOOGLE_API_KEY
#     }

#     async with httpx.AsyncClient() as client:
#         response = await client.get(url, params=params)

#     data = response.json()

#     if data["status"] != "OK":
#         raise Exception("Directions API failed")

#     return data["routes"]

import httpx
import os

GOOGLE_API_KEY = os.getenv("GOOGLE_MAPS_API_KEY")

ROUTES_URL = "https://routes.googleapis.com/directions/v2:computeRoutes"


async def fetch_routes(origin, destination):
    """
    Calls Google Routes API (ComputeRoutes)
    and returns alternative routes.
    """

    headers = {
        "Content-Type": "application/json",
        "X-Goog-Api-Key": GOOGLE_API_KEY,

        # VERY IMPORTANT → controls response size
        "X-Goog-FieldMask":
            "routes.polyline.encodedPolyline,"
            "routes.distanceMeters,"
            "routes.duration"
    }

    body = {
        "origin": {
            "location": {
                "latLng": {
                    "latitude": origin[0],
                    "longitude": origin[1]
                }
            }
        },
        "destination": {
            "location": {
                "latLng": {
                    "latitude": destination[0],
                    "longitude": destination[1]
                }
            }
        },

        # multiple routes
        "computeAlternativeRoutes": True,

        "travelMode": "WALK",

        # best polyline quality for ML
        "polylineQuality": "HIGH_QUALITY"
    }

    async with httpx.AsyncClient() as client:
        response = await client.post(
            ROUTES_URL,
            headers=headers,
            json=body
        )

    data = response.json()

    if "routes" not in data:
        raise Exception(f"Routes API failed: {data}")

    return data["routes"]