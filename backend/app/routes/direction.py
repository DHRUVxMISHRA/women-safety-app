from fastapi import APIRouter, HTTPException
from app.schemas.route_schema import RouteRequest, RouteResponse
from app.services.risk_service import rank_routes

router = APIRouter(prefix="/routes", tags=["Route Risk"])


@router.post("/safe-route", response_model=RouteResponse)
def get_safe_route(data: RouteRequest):
    """
    Receives multiple navigation routes,
    calculates ML risk score,
    returns safest route.
    """

    if not data.routes:
        raise HTTPException(
            status_code=400,
            detail="No routes provided"
        )

    # ---------- ML ranking ----------
    safest_route = rank_routes(data.routes)

    # ---------- response ----------
    return RouteResponse(
        safest_route_index=safest_route.index,
    )