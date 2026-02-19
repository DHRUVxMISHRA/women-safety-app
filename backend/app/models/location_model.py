from pydantic import BaseModel, Field
from datetime import datetime

class LocationPointDB(BaseModel):
    latitude: float
    longitude: float
    updated_at: datetime
