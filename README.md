# Modal-Payment

To use the modal payment feature first install the app, then you have two options:

1 - send an Intent from your application with a float extra with the intent key "price_intent_key".

2 - Share a float value from your application with Intent.ACTION = "android.intent.action.SEND", and Intent.type = "text/plain".
Note that you can share a text with a price value from anywhere. Just select the text, and share it with the application.
