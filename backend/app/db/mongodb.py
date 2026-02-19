from pymongo import MongoClient
from typing import Optional
from pymongo.database import Database
from pymongo.collection import Collection
import os
MONGO_URL = os.getenv("MONGO_URI")
DB_NAME = "women_safety"

class MongoManager:
    _client: Optional[MongoClient] = None

    @classmethod
    def connect(cls):
        cls._client = MongoClient(
            MONGO_URL,
            serverSelectionTimeoutMS=2000
        )
        cls._client.admin.command("ping")
        print("✅ MongoDB connected")

    @classmethod
    def close(cls):
        if cls._client:
            cls._client.close()
            print("❌ MongoDB disconnected")

    @classmethod
    def get_db(cls) -> Database:
        """Guarantee client exists."""
        if cls._client is None:
            raise RuntimeError("MongoDB not connected")
        return cls._client[DB_NAME]

    @classmethod
    def get_collection(cls, name: str) -> Collection:
        return cls.get_db()[name]
