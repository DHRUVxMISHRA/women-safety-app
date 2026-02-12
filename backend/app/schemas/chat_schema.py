from pydantic import BaseModel
# validates the request user made
# check types, if type mismatch -> Fastapi responds - 422 Unprocessable Entity

class ChatRequest(BaseModel):
    message: str
