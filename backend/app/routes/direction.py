from fastapi import APIRouter
from app.core.config import GOOGLE_API_KEY as G_KEY
from app.services.gmaps_service import get_routes
from app.services.risk_service import rank_routes

router = APIRouter(prefix="/routes", tags=["Google Maps"])

GOOGLE_API_KEY = G_KEY

DIRECTIONS_URL = "https://maps.googleapis.com/maps/api/directions/json"


@router.get("/safe-route")
def get_safe_route(origin: str, destination: str):
    routes = get_routes(origin, destination) #[{},{},{}] contains a list of json routes 
    safe_route_index = rank_routes(routes)# risk score is added to all routes ,
    return safe_route_index # safe route is the route_index with minimum risk score
# call the api from app with same settings then pick the route according to the safe_route index