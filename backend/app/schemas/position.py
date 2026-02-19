from pydantic import BaseModel
from datetime import datetime

class Location(BaseModel):
    latitude: float
    longitude: float
    time: datetime