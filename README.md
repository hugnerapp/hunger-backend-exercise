# hunger-backend-exercise


### Goal 

* 請 Fork 此 Repository
* 開發一個 Backend Application
* 發送 Pull Request 到此 Repository

#### 使用技術
    * Java 8
    * Kotlin
    * Gradle  
    * Spring Boot 2.6.x
    * Spring Data JPA
    * H2 Database, mode=MySQL

### Requirement

#### 需求描述：店家與設計師維護
    
* 店家欄位需求
  * id
  * 商家代碼，系統產生，6碼英數字亂碼，不可重複  
  * 店家名稱，不可重複

* 設計師欄位需求
  * id
  * 設計師名稱

* 設計師服務時段
  * 禮拜幾
  * 上班時間
  * 下班時間


#### API 需求

* API的Request/Response格式皆為 json
* API的日期時間欄位，格式皆為 ISO-8601

##### API
* 新增店家
* 更新店家
* 刪除店家
* 取得單筆店家資料，包含其下的設計師資料
* 新增店家下的設計師及服務時段
* 更新店家下的設計師及服務時段
* 刪除店家下的設計師
* 取得單筆設計師，包含所屬店家及其服務時段  
* 店家列表，可以分頁，可透過店家名稱或設計師名稱查詢到店家
* 以日期查詢有服務的設計師及所屬店家


### Check Point

#### 基本項目
- [ ] 專案可以正常編譯 
- [ ] 專案可以正常執行 
- [ ] 專案的程式碼風格及排版一致 
- [ ] 專案可以透過指令 `.gradlew test` 執行測試並通過 
- [ ] 店家API都可以正常呼叫並取得預期的結果
- [ ] 設計師API都可以正常呼叫並取得預期的結果
- [ ] 專案可以透過 application.properties 設定切換到 MySQL資料庫

#### 加分項目
- [ ] 執行測試時，可以看到實際執行時產生的SQL
- [ ] 呈上，進行API查詢時，沒有多餘的不必要的SQL (例如 N+1 query)
- [ ] API發生錯誤時，有一致的回應結構
- [ ] 專案有使用 `OpenApi` 或 `Swagger`，在程式啟動後可以透過 url 訪問 swagger (例如：https://petstore.swagger.io/)  
- [ ] 專案有使用 `jacoco` 或 `kover` 統計 Coverage
- [ ] 呈上，專案的 Service Layer Coverage在 80% 以上
- [ ] 執行兩個 instance，並嘗試多次同時呼叫店家API，依然可以避免商家代碼重複
