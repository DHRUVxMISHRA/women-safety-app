from pydantic import BaseModel

class SOSRequest(BaseModel):
    user_id: str
    name: str
    latitude: float
    longitude: float
    emergency_contact: str
