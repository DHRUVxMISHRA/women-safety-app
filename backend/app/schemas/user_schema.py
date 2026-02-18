from pydantic import BaseModel

class User(BaseModel):
    name: str
    contact: str
    gmail: str
    adhaar: str