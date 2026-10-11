import json, os, glob, hashlib, re

log_path = r'C:\Users\ADMIN\.gemini\antigravity-ide\brain\207e3c26-93bc-415b-b1bc-891410ed9330\.system_generated\logs\transcript_full.jsonl'

# Let's search commands and results in the transcript
with open(log_path, 'r', encoding='utf-8') as f:
    for line in f:
        d = json.loads(line)
        content = d.get('content', '')
        # Check for TWCore path in 7z output or listing
        if 'TWCore' in content and ('priv-app' in content or 'system' in content):
            for l in content.split('\n'):
                if 'TWCore' in l:
                    print('TWCore line:', l)
        # Check MQTT connection
        if 'tcp://' in content:
            for l in content.split('\n'):
                if 'tcp://' in l:
                    print('tcp line:', l)
        # Check MQTT topics
        if 'mqtt' in content.lower() and ('topic' in content.lower() or 'subscribe' in content.lower()):
            for l in content.split('\n'):
                if any(k in l.lower() for k in ['sub', 'topic', 'filter', '#', 'update']):
                    print('mqtt topic line:', l[:120])
