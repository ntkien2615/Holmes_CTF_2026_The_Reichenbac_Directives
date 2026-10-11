import os, sys, json, re, hashlib

log_path = r'C:\Users\ADMIN\.gemini\antigravity-ide\brain\207e3c26-93bc-415b-b1bc-891410ed9330\.system_generated\logs\transcript_full.jsonl'

# 1. Check Q1: TWCore.apk location in system.img
# Let's search transcript for where TWCore was found or 7z listing of system.img
print("--- Searching Q1, Q2, Q3, Q4 in transcript ---")
with open(log_path, 'r', encoding='utf-8') as f:
    for line in f:
        if any(w in line for w in ['TWCore.apk', 'persist.', 'ro.', 'tcp://', 'authtoken', 'topic', 'jarservice-v1.12.apk']):
            d = json.loads(line)
            c = d.get('content', '')
            if 'priv-app' in c and 'TWCore' in c:
                for l in c.split('\n'):
                    if 'TWCore' in l:
                        print("Q1 line:", l)
            if 'persist.' in c or 'ro.' in c:
                for l in c.split('\n'):
                    if 'jy(Context' in l or 'SystemProperties' in l or 'persist.' in l or 'ro.' in l:
                        if 'Property:' in l or 'get(' in l:
                            print("Q2 prop line:", l)
            if 'tcp://' in c:
                for l in c.split('\n'):
                    if 'tcp://' in l:
                        print("Q3 tcp line:", l)
            if 'topic' in c.lower() and ('/' in c):
                for l in c.split('\n'):
                    if any(k in l.lower() for k in ['sub', 'filter', 'topic', '#']):
                        if len(l.strip()) < 150:
                            print("Q4 topic line:", l.strip())
