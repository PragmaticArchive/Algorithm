l, n = map(int, input().split())
s = input()

ans = ""

i = 0
while i < len(s):
    if s[i] == 'w':
        buffer = [0]
        idx = 0
        lastletter = 'w'
        while s[i] in ('w', 'h'):
            if lastletter == s[i]:
                buffer[idx] += 1
            else:
                buffer.append(1)
                idx += 1
                lastletter = s[i]
            i += 1
            if (i >= len(s)):
                break
        if len(buffer) % 2 == 0:
            buffer.append(0)
        headache = n
        for idx in range(0, len(buffer) - 1, 2):
            if headache <= buffer[idx]:
                buffer[idx] -= headache
                buffer[-1] += headache
                break
            else:
                headache -= buffer[idx]
                buffer[-1] += buffer[idx]
                buffer[idx] = 0
        for idx in range(len(buffer)):
            if idx % 2 == 0:
                ans = ans + 'w' * buffer[idx]
            else:
                ans = ans + 'h' * buffer[idx]
    else:
        ans = ans + s[i]
        i += 1
print(ans)
