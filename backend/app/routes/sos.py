from fastapi import APIRouter, HTTPException
from app.schemas.sos_schema import SOSRequest
from app.services.twilio_service import (
    send_sos_sms,
    send_sos_whatsapp
    )

router = APIRouter()

@router.post("/sos")
async def trigger_sos(data: SOSRequest):

    try:
        location_link = f"https://maps.google.com/?q={data.latitude},{data.longitude}"

        sms_sid = send_sos_sms(
            to_number=data.emergency_contact,
            location_link=location_link,
            name=data.name
        )

        whatsapp_sid = send_sos_whatsapp(
            to_number=data.emergency_contact,
            location_link=location_link,
            name=data.name
        )
        return {
            "status": "SOS Sent",
            "sms_sid": sms_sid,
            "whatsapp_sid": whatsapp_sid
        }
    
    except Exception as e:
        raise HTTPException(
            status_code = 500,
            detail = f"SOS Failed: {str(e)}"
        )
