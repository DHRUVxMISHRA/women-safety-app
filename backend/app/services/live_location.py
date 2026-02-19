# from datetime import datetime
# from app.db.mongodb import loc
# from app.models.location_model import LocationDB


# def update_location(data):

#     location = LocationDB(
#         user_id=data.user_id,
#         latitude=data.latitude,
#         longitude=data.longitude,
#         updated_at=datetime.utcnow()
#     )

#     loc.update_one(
#         {"_id": location.user_id},
#         {"$set": location.model_dump(by_alias=True)},
#         upsert=True
#     )

#     return True


# def get_location(user_id: str):

#     doc = loc.find_one({"_id": user_id})

#     if not doc:
#         return None

#     location = LocationDB(**doc)

#     return {
#         "user_id": location.user_id,
#         "latitude": location.latitude,
#         "longitude": location.longitude,
#     }
