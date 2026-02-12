from fastapi import APIRouter
from app.schemas.chat_schema import ChatRequest
from app.services.gpt_service import generate_response
from app.services.memory_service import (
    get_or_create_conversation,
    add_user_message,
    add_assistant_message
)

router = APIRouter()


# route for chatbot - Sakhi
@router.post("/chat/{user_id}")
def chat(user_id: str, request: ChatRequest):

    # convo so far
    conversation = get_or_create_conversation(user_id)

    # appends user message in convo
    add_user_message(user_id, request.message)

    # get response from api
    response = generate_response(conversation)

    # appends assistant message in convo
    add_assistant_message(user_id, response)

    return {"response": response}
