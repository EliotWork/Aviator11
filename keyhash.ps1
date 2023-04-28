& ($env:JAVA_HOME + 'C:\Program Files\Java\jre1.8.0_341\bin\keytool.exe') -list -v -keystore key.jks -alias key0 -storepass (Get-Content key.txt)[1] `
| Select-String '^\s+SHA1: ([:0-9A-F]+)$' `
| ForEach-Object { $_.Matches.Groups[1].value -replace '\W' -replace '..', '0x$& ' } `
| ForEach-Object { [System.Convert]::ToBase64String([byte[]] -split $_) } `
| ForEach-Object { ('Хэш ключа (Base64) — ' + $_) }