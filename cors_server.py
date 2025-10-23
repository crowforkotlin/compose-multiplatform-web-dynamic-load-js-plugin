import http.server
import socketserver
import os
import sys

# 預設端口
PORT = 8080

# --- 新增的程式碼開始 ---
# 檢查是否有提供路徑參數
if len(sys.argv) > 1:
    # 如果有，就使用第二個參數作為目標路徑
    # sys.argv[0] 是腳本名稱本身
    target_path = sys.argv[1]
    try:
        # 切換到指定的目錄
        os.chdir(target_path)
    except FileNotFoundError:
        print(f"錯誤：找不到目錄 '{target_path}'")
        # 找不到目錄就退出
        sys.exit(1)
# --- 新增的程式碼結束 ---

class CORSRequestHandler(http.server.SimpleHTTPRequestHandler):
    def end_headers(self):
        self.send_header('Access-Control-Allow-Origin', '*')
        self.send_header('Access-Control-Allow-Methods', 'GET, POST, OPTIONS')
        self.send_header('Access-Control-Allow-Headers', 'X-Requested-With, Content-Type')
        super().end_headers()

# 啟動伺服器
with socketserver.TCPServer(("", PORT), CORSRequestHandler) as httpd:
    # 顯示當前服務的目錄
    print(f"正在 {os.getcwd()} 目錄的 {PORT} 端口上提供支援 CORS 的服務...")
    httpd.serve_forever()