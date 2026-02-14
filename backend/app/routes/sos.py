from fastapi import APIRouter, HTTPException
from app.schemas.sos_schema import SOSRequest
from app.services.twilio_service import send_sos_whatsapp
from app.services.contact_service import get_user_contacts

router = APIRouter(prefix="/users",tags=["Emergency-SOS"])

# url - >  /users/sos
@router.post("/sos")
async def trigger_sos(data: SOSRequest):

    
    location_link = f"https://maps.google.com/?q={data.latitude},{data.longitude}"

    contacts = get_user_contacts(data.user_id)
                            
    if not contacts:
        raise HTTPException(
            status_code=404,
            detail="No Emergency contacts found!"
        )
        
    results = []

    try:
        for contact in contacts:
            whatsapp_sid = send_sos_whatsapp(
                to_number=contact["phone"],
                location_link=location_link,
                name=data.name
            )

            results.append({
                "contact_name": contact["name"],
                "phone": contact["phone"],
                "whatsapp_sid": whatsapp_sid
            })
    except Exception as e:
        raise HTTPException(
            status_code = 500,
            detail = f"SOS Failed while sending message: {str(e)}"
        )
    return {
        "status": "SOS Sent Succesfully",
        "total_contacts": len(results),
        "details": results
    }
