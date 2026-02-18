from app.services.polyline_service import decode_and_sample
from app.ml_models.ml_model import predict_route_risk

def rank_routes(routes): #  [ {},{},{}] 3 routes
    i=0
    for route in routes:
        coords = decode_and_sample(route["polyline"]) # return np array of coords
        risk = predict_route_risk(coords)
        route["risk_score"] = float(risk)
        route["index"] = i
        i += 1

    min_route= min(routes, key=lambda x: x["risk_score"])# Find the element in the list with the smallest value of the key.
    return min_route["index"]
