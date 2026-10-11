import json, sys

with open(r'C:\Users\ADMIN\.gemini\antigravity-ide\brain\207e3c26-93bc-415b-b1bc-891410ed9330\.system_generated\logs\transcript.jsonl', 'r', encoding='utf-8') as f:
    lines = f.readlines()

with open('scratch_trans_summary.txt', 'w', encoding='utf-8') as out:
    for line in lines:
        try:
            d = json.loads(line)
        except Exception:
            continue
        typ = d.get('type')
        content = d.get('content', '')
        idx = d.get('step_index')
        tool_calls = d.get('tool_calls', [])
        
        # Check tool calls
        if tool_calls:
            for tc in tool_calls:
                call_info = f"CALL: {tc.get('id')} -> {tc.get('type')} {list(tc.keys())}"
                # if it's run_command
                if 'run_command' in str(tc):
                    cmd = tc.get('run_command', {}).get('CommandLine') or tc.get('arguments', {}).get('CommandLine', '')
                    out.write(f"Step {idx} TOOL_CALL CMD: {cmd}\n")
        
        if typ == 'PLANNER_RESPONSE' and content:
            out.write(f"\n=== STEP {idx} PLANNER_RESPONSE ===\n")
            out.write(content + "\n")
        elif typ == 'RUN_COMMAND' or typ == 'TOOL_RESULT':
            # print snippet of result
            out.write(f"Step {idx} RESULT snippet: {str(content)[:300]}\n")
