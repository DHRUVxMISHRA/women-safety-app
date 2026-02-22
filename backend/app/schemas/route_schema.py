from pydantic import BaseModel
from typing import List, Optional


class Coordinate(BaseModel):
    lat: float
    lng: float


class Route(BaseModel):
    distance: float
    duration: float
    coordinates: List[Coordinate]

    risk_score: Optional[float] = None
    index: Optional[int] = None


class RouteRequest(BaseModel):
    firebase_token: str
    routes: List[Route]

class RouteResponse(BaseModel):
    safest_route_index: int