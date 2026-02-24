from fastapi import APIRouter, HTTPException
from app.schemas.route_schema import RouteRequest, RouteResponse
from app.services.risk_service import rank_routes
from app.services.directions_service import fetch_routes
from app.services.polyline_service import decode_route_polyline
from app.models.route_model import Route
router = APIRouter(prefix="/routes", tags=["Route Risk"])


@router.post("/safe-route", response_model=RouteResponse)
async def get_safe_route(data: RouteRequest):
    """
    1. Fetch routes from Google Directions API
    2. Decode polylines into coordinates
    3. Run ML risk ranking
    4. Return safest route
    """

    # ---------- Fetch routes ----------
    routes = await fetch_routes(
        origin=(data.origin_lat, data.origin_lng),
        destination=(data.dest_lat, data.dest_lng)
    )

    if not routes:
        raise HTTPException(
            status_code=404,
            detail="No routes found"
        )

    # ---------- Convert polylines ----------
    processed_routes = []

    for route in routes:
        coords = decode_route_polyline(route)

        processed_routes.append(
        Route(coordinates=coords)
)

    # ---------- ML ranking ----------
    safest_route = rank_routes(processed_routes)

    # ---------- Response ----------
    return RouteResponse(
        safest_route_index=safest_route.index
    )