from fastapi import FastAPI
from app.routes.chat import router as chat_router
from app.routes.sos import router as sos_router
from app.routes.contact_routes import router as contact_router
from app.routes.direction import router as gmaps
from app.routes.register import router as user

app = FastAPI()

# Sakhi- Chat-Bot
app.include_router(chat_router)

# sos - messaging
app.include_router(sos_router)

# add emergency contact
app.include_router(contact_router)

# add gmaps routes
app.include_router(gmaps)

# add new user
app.include_router(user)