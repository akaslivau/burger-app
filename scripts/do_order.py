import asyncio
import json

import aiohttp

from kafka_core import send_message, get_uuid

# kafka
BROKER = '192.168.31.152:9092'
TOPIC = 'order-burger-command'

# rest
URL = 'http://localhost:7081/burger'
headers = {
  'Content-Type': 'application/json'
}

# list
orders = [
  {'source': 'kafka', 'name': 'SIMPLE_HAMBURGER', 'count': 10},
  {'source': 'kafka', 'name': 'SIMPLE_HAMBURGER', 'count': 10},
  {'source': 'kafka', 'name': 'SIMPLE_HAMBURGER', 'count': 10},
  {'source': 'rest', 'name': 'SIMPLE_HAMBURGER', 'count': 10},
  {'source': 'rest', 'name': 'SIMPLE_HAMBURGER', 'count': 10},
  {'source': 'rest', 'name': 'SIMPLE_HAMBURGER', 'count': 10}
]

kafka = [x for x in orders if x['source'] == 'kafka']
rest = [x for x in orders if x['source'] == 'rest']


async def kafka_async():
  ret = await asyncio.gather(
      *[kafka_msg(payload) for payload in kafka])
  print("Kafka finalized all. Len {}.".format(len(ret)))


async def kafka_msg(item):
  del item['source']
  msg = json.dumps(item)
  kafka_headers = [
    ('uuid', get_uuid()),
  ]
  send_message(BROKER, TOPIC, msg, kafka_headers)


async def rest_async():
  async with aiohttp.ClientSession(headers=headers) as session:
    ret = await asyncio.gather(
        *[post(session, json.dumps(payload)) for payload in rest])
  print("Rest finalized all. Len {}.".format(len(ret)))


async def post(session, json_data):
  try:
    async with session.post(url=URL, data=json_data) as response:
      await response.read()
  except Exception as e:
    print("Error {}.".format(e.__class__))


asyncio.run(rest_async())
asyncio.run(kafka_async())
