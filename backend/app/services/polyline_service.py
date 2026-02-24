import polyline
from app.models.coordinate_model import Coordinate

def decode_route_polyline(route):
    """
    Decode polyline from Google Routes API response.
    """

    try:
        encoded = route["polyline"]["encodedPolyline"]
    except KeyError:
        raise ValueError(
            f"Polyline not found in route response: {route}"
        )

    coords = polyline.decode(encoded)

    return [
        Coordinate(lat,lng)
        for lat, lng in coords
    ]