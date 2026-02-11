from fastapi import FastAPI
from app.routes.chat import router as chat_router

app = FastAPI()

# Sakhi- Chat-Bot
app.include_router(chat_router)
