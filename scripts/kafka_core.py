import uuid

from kafka import KafkaProducer
from kafka.errors import KafkaError
from kafka.future import log


def send_message(broker, topic, message, headers):
    producer = KafkaProducer(bootstrap_servers=[broker])
    # Asynchronous by default
    future = producer.send(topic=topic, value=str.encode(message), headers=headers)
    # Block for 'synchronous' sends
    try:
        record_metadata = future.get(timeout=10)
    except KafkaError:
        # Decide what to do if produce request failed...
        log.exception()
        pass
    # Successful result returns assigned partition and offset
    print("Sent to topic: {}".format(record_metadata.topic))
    print("Partition: {}".format(record_metadata.partition))
    print("Offset: {}".format(record_metadata.offset))


def get_message(filename, message):
    if len(filename) > 0:
        with open(filename, 'r', encoding="utf8") as file:
            return file.read()
    else:
        return message


def get_uuid():
    return str.encode(str(uuid.uuid4()))
