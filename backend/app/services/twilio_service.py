# from twilio.rest import Client
# from app.core.config import (
#     TWILIO_ACCOUNT_SID,
#     TWILIO_AUTH_TOKEN,
#     TWILIO_PHONE_NUMBER,
# )

# client = Client(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN)


# def send_sos_sms(to_number: str, location_link: str, name: str):
#     message = client.messages.create(
#         body=f"EMERGENCY!\n{name} needs help.\nLocation: {location_link}",
#         from_=TWILIO_PHONE_NUMBER,
#         to=to_number,
#     )
#     return message.sid
from twilio.rest import Client
from app.core.config import (
    TWILIO_ACCOUNT_SID,
    TWILIO_AUTH_TOKEN,
    TWILIO_PHONE_NUMBER,
    TWILIO_WHATSAPP_NUMBER,   # Add this in config
)

client = Client(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN)


# -------------------- SMS SOS -------------------- #
def send_sos_sms(to_number: str, location_link: str, name: str):
    message = client.messages.create(
        body=f"EMERGENCY!\n{name} needs help.\nLocation: {location_link}",
        from_=TWILIO_PHONE_NUMBER,
        to=to_number,
    )
    return message.sid


# -------------------- WhatsApp SOS -------------------- #
def send_sos_whatsapp(to_number: str, location_link: str, name: str):
    message = client.messages.create(
        body=f"EMERGENCY!\n{name} needs help.\nLocation: {location_link}",
        from_=f"whatsapp:{TWILIO_WHATSAPP_NUMBER}",
        to=f"whatsapp:{to_number}",
    )
    return message.sid
