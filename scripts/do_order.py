import asyncio
import json
import uuid

import aiohttp
from kafka import KafkaProducer

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
  {'source': 'kafka', 'name': 'SIMPLE_HAMBURGER', 'count': 10, 'times': 10},
  # {'source': 'rest', 'name': 'SIMPLE_HAMBURGER', 'count': 10, 'times': 10},
]

kafka = [x for x in orders if x['source'] == 'kafka']
rest = [x for x in orders if x['source'] == 'rest']


async def kafka_async():
  p = KafkaProducer(bootstrap_servers=[BROKER])
  payloads = flatten(kafka)
  coroutines = [kafka_msg(payload, p) for payload in payloads]
  await asyncio.gather(*coroutines)
  print('Kafka sent')


async def kafka_msg(item, producer):
  msg = json.dumps(item)
  kafka_headers = [
    ('uuid', str.encode(str(uuid.uuid4()))),
  ]
  producer.send(topic=TOPIC, value=str.encode(msg), headers=kafka_headers)


async def rest_async():
  async with aiohttp.ClientSession(headers=headers) as session:
    payloads = flatten(rest)
    coroutines = [post(session, json.dumps(payload)) for payload in payloads]
    await asyncio.gather(*coroutines)
  print("Rest sent")


async def post(session, json_data):
  try:
    async with session.post(url=URL, data=json_data) as response:
      await response.read()
  except Exception as e:
    print("Error {}.".format(e.__class__))


def flatten(items):
  result = []
  for item in items:
    times = item['times']
    del item['times']
    del item['source']
    result.extend([item] * times)
  return result


asyncio.run(rest_async())
asyncio.run(kafka_async())
