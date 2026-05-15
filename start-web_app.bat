@echo off
cd web_app

if /i "%~1"=="prod" goto :prod

:dev
cd web_app
npm install
npm run dev
goto :eof

:prod
npm run build
python -m http.server --directory dist 5173

:eof
cd ..