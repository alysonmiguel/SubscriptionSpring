#!/bin/bash

echo -e "\n\n Provisionando um Grupo de Serviço para MQTT" &&
sleep 2s &&
curl -iX POST \
  'http://localhost:4041/iot/services' \
  -H 'Content-Type: application/json' \
  -H 'fiware-service: openiot' \
  -H 'fiware-servicepath: /' \
  -d '{
 "services": [
   {
     "apikey":      "4jggokgpepnvsb2uv4s40d59ov",
     "cbroker":     "http://orion:1026",
     "entity_type": "Thing",
     "resource":    ""
   }
 ]
}'

echo -e "\n\n Limpando dados da lampada" &&
sleep 2s &&

curl -L -X DELETE 'http://localhost:4041/iot/devices/lamp001' \
-H 'fiware-service: openiot' \
-H 'fiware-servicepath: /' \
--data-raw ''

echo -e "\n\n Limpando dados da Sensor de movimento" &&
sleep 2s &&

curl -L -X DELETE 'http://localhost:4041/iot/devices/motion001' \
-H 'fiware-service: openiot' \
-H 'fiware-servicepath: /' \
--data-raw ''


echo -e "\n\n Provisionando a Lampada" &&
sleep 2s &&

curl -L -X POST 'http://localhost:4041/iot/devices' \
-H 'Content-Type: application/json' \
-H 'fiware-service: openiot' \
-H 'fiware-servicepath: /' \
--data-raw '{
  "devices": [
    {
      "device_id": "lamp001",
      "entity_name": "urn:ngsi-ld:Lamp:001",
      "entity_type": "Lamp",
      "protocol": "PDI-IoTA-UltraLight",
       "transport": "MQTT",
      "commands": [ 
        {"name": "on","type": "command"},
        {"name": "off","type": "command"}
       ],
       "attributes": [
       	{"object_id": "s", "name": "state", "type":"Text"},
        {"object_id": "l", "name": "luminosity", "type":"Integer"}
       ],
       "static_attributes": [
         {"name":"refStore", "type": "Relationship","value": "urn:ngsi-ld:Store:001"}
    	]
    }
  ]
}'


echo -e "\n\n Provisionando um Sensor" &&
sleep 2s &&

curl -iX POST \
  'http://localhost:4041/iot/devices' \
  -H 'Content-Type: application/json' \
  -H 'fiware-service: openiot' \
  -H 'fiware-servicepath: /' \
  -d '{
 "devices": [
   {
     "device_id":   "motion001",
     "entity_name": "urn:ngsi-ld:Motion:001",
     "entity_type": "Motion",
     "protocol":    "PDI-IoTA-UltraLight",
     "transport":   "MQTT",
     "timezone":    "Europe/Berlin",
     "attributes": [
       { "object_id": "c", "name": "count", "type": "Integer" }
     ],
     "static_attributes": [
       { "name":"refStore", "type": "Relationship", "value": "urn:ngsi-ld:Store:001"}
     ]
   }
 ]
}
'


echo -e "\n\n Provisionando um Atuador - Bell" &&
sleep 2s &&

curl -iX POST \
  'http://localhost:4041/iot/devices' \
  -H 'Content-Type: application/json' \
  -H 'fiware-service: openiot' \
  -H 'fiware-servicepath: /' \
  -d '{
  "devices": [
    {
      "device_id": "bell001",
      "entity_name": "urn:ngsi-ld:Bell:001",
      "entity_type": "bell_001",
      "protocol": "PDI-IoTA-UltraLight",
      "transport": "MQTT",
      "commands": [
        { "name": "ring", "type": "command" }
       ],
       "static_attributes": [
         {"name":"refStore", "type": "Relationship","value": "urn:ngsi-ld:Store:001"}
      ]
    }
  ]
}
'

echo -e "\n\n Provisionando uma porta inteligente door" &&
sleep 2s &&

curl -L -X POST 'http://localhost:4041/iot/devices' \
-H 'Content-Type: application/json' \
-H 'fiware-service: openiot' \
-H 'fiware-servicepath: /' \
--data-raw '{
  "devices": [
    {
      "device_id": "door001",
      "entity_name": "urn:ngsi-ld:Door:001",
      "entity_type": "Door",
      "protocol": "PDI-IoTA-UltraLight",
      "transport": "MQTT",
      "commands": [ 
        {"name": "unlock","type": "command"},
        {"name": "open","type": "command"},
        {"name": "close","type": "command"},
        {"name": "lock","type": "command"}
       ],
       "attributes": [
       	{"object_id": "s", "name": "state", "type":"Text"}
       ],
       "static_attributes": [
         {"name":"refStore", "type": "Relationship","value": "urn:ngsi-ld:Store:001"}
       ]
    }
  ]
}'


echo -e "\n\n Deletando entidades desnecessárias." &&
sleep 2s &&

curl -L -X DELETE 'http://localhost:1026/v2/entities/Thing:motion004?type=Thing' \
-H 'fiware-service: openiot' \
-H 'fiware-servicepath: /'

curl -L -X DELETE 'http://localhost:1026/v2/entities/Thing:motion003?type=Thing' \
-H 'fiware-service: openiot' \
-H 'fiware-servicepath: /'

curl -L -X DELETE 'http://localhost:1026/v2/entities/Thing:motion002?type=Thing' \
-H 'fiware-service: openiot' \
-H 'fiware-servicepath: /'


curl -L -X DELETE 'http://localhost:1026/v2/entities/Thing:lamp004?type=Thing' \
-H 'fiware-service: openiot' \
-H 'fiware-servicepath: /'

curl -L -X DELETE 'http://localhost:1026/v2/entities/Thing:lamp003?type=Thing' \
-H 'fiware-service: openiot' \
-H 'fiware-servicepath: /'

curl -L -X DELETE 'http://localhost:1026/v2/entities/Thing:lamp002?type=Thing' \
-H 'fiware-service: openiot' \
-H 'fiware-servicepath: /'



