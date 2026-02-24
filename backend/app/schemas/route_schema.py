from pydantic import BaseModel
from typing import List


class RouteRequest(BaseModel):
    origin_lat: float
    origin_lng: float
    dest_lat: float
    dest_lng: float


class RouteResponse(BaseModel):
    safest_route_index: int