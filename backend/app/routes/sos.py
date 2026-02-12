from fastapi import APIRouter
from app.schemas.sos_schema import SOSRequest
from app.services.twilio_service import send_sos_sms

router = APIRouter()

@router.post("/sos")
async def trigger_sos(data: SOSRequest):

    location_link = f"https://maps.google.com/?q={data.latitude},{data.longitude}"

    sid = send_sos_sms(
        to_number=data.emergency_contact,
        location_link=location_link,
        name=data.name
    )

    return {
        "status": "SOS Sent",
        "message_sid": sid
    }

# https://maps.google.com/?q=24.8829,74.6230
# chittorgarh cordinates:

# lat = 24.8829
# lon = 74.6230