from fastapi import APIRouter, HTTPException
from app.schemas.position import (
    LocationUpdate,
    LocationResponse
)
from app.services.live_location import (
    update_location,
    get_location
)

router = APIRouter(
    prefix="/location",
    tags=["Live-Location"]
)

@router.post("/update")
def update(data: LocationUpdate):
    update_location(data)
    return {"message": "Location updated"}


@router.get("/{user_id}", response_model=LocationResponse)
def fetch(user_id: int):
    location = get_location(user_id)
    if not location:
        raise HTTPException(
            status_code=404,
            detail="Location not found"
        )
    return location
