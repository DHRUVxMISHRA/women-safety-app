from fastapi import FastAPI
from app.routes.chat import router as chat_router
from app.routes.sos import router as sos_router
from app.routes.contact_routes import router as contact_router
from app.routes.direction import router as safe
from app.routes.register import router as user
from app.routes.location_routes import router as location_router
from fastapi.middleware.cors import CORSMiddleware


from contextlib import asynccontextmanager
from app.db.mongodb import MongoManager

@asynccontextmanager
async def lifespan(app: FastAPI):
    print("Starting app...")
    await MongoManager.connect()
    yield
    print("Shutting down app...")
    await MongoManager.close()

app = FastAPI(lifespan=lifespan)

# backend testing using ngrok
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # dev only
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)



# Sakhi- Chat-Bot
app.include_router(chat_router)

# sos - messaging
app.include_router(sos_router)

# add emergency contact
app.include_router(contact_router)

# add safe route 
app.include_router(safe)

# add new user
app.include_router(user)

# live location
app.include_router(location_router)