from fastapi import FastAPI
from app.routes.chat import router as chat_router
from app.routes.sos import router as sos_router
app = FastAPI()

# Sakhi- Chat-Bot
app.include_router(chat_router)

# sos - messaging
app.include_router(sos_router)