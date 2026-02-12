from app.core.config import SYSTEM_PROMPT
# only storing history in memory
# so it is volatile. need to add database
conversation_store = {}

# returns the curent convo so far
def get_or_create_conversation(user_id: str):
    if user_id not in conversation_store:
        conversation_store[user_id] = [
            {"role": "system", "content": SYSTEM_PROMPT}
        ]
    return conversation_store[user_id]

# add the user message
def add_user_message(user_id: str, message: str):
    conversation_store[user_id].append(
        {"role": "user", "content": message}
    )

# add assistant message
def add_assistant_message(user_id: str, message: str):
    conversation_store[user_id].append(
        {"role": "assistant", "content": message}
    )
