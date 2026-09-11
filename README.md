# LagFixMod (Forge 1.20.1)

Mod tối ưu hiệu năng đơn giản:
1. Gom các viên XP orb gần nhau thành 1 entity.
2. Giảm tần suất cập nhật AI cho quái ở xa người chơi.
3. (Tùy chọn, mặc định TẮT) Giới hạn số quái chen chúc trong 1 khu vực nhỏ.

Tất cả chỉnh được trong file `config/lagfixmod-common.toml` sau khi chạy game lần đầu (không cần build lại mod).

---

## Không có máy tính? Build ngay trên điện thoại qua GitHub Actions (khuyên dùng)

Project này đã có sẵn file `.github/workflows/build.yml` để GitHub tự build hộ bạn trên "máy chủ đám mây" — không cần cài Java/Gradle gì trên điện thoại cả, chỉ cần trình duyệt.

1. Cài app **ZArchiver** (hoặc app giải nén bất kỳ) trên điện thoại, giải nén file `LagFixMod.zip`.
2. Mở trình duyệt (Chrome), vào **github.com** → **Sign up** tạo tài khoản miễn phí (nếu chưa có).
3. Bấm nút **+** góc trên phải → **New repository** → đặt tên (vd `LagFixMod`) → để **Public** → **Create repository**.
4. Trong repo vừa tạo, bấm **Add file** → **Upload files** → chọn toàn bộ file/thư mục đã giải nén ở bước 1 (bao gồm cả thư mục `.github` — nếu điện thoại ẩn thư mục bắt đầu bằng dấu chấm, dùng ZArchiver để "hiện file ẩn" trước khi chọn) → **Commit changes**.
5. Vào tab **Actions** ở trên cùng repo → sẽ thấy workflow "Build LagFixMod" tự chạy (khoảng 3–5 phút, có vòng tròn vàng đang xoay).
6. Khi chạy xong (dấu tích xanh ✅) → bấm vào lần chạy đó → kéo xuống mục **Artifacts** → tải file `lagfixmod-jar.zip` về điện thoại → giải nén ra được file `.jar` thật, copy vào `mods` của ZalithLauncher là chơi được.

---

## Cách 2 — Build trên máy tính (nếu sau này có máy)

### Bước 1 — Cài công cụ cần thiết

1. **Java 17 (JDK)** — tải bản Temurin 17 tại: https://adoptium.net (chọn đúng hệ điều hành, cài đặt bình thường).
2. **IntelliJ IDEA Community** (miễn phí) — tải tại: https://www.jetbrains.com/idea/download
   (IDE này tự nhận diện file `build.gradle`, tự tải Gradle + thư viện Forge, không cần bạn cài Gradle tay).

## Bước 2 — Mở project

1. Giải nén file `LagFixMod.zip` bạn vừa tải về máy tính.
2. Mở IntelliJ IDEA → **Open** → chọn thư mục `LagFixMod` vừa giải nén.
3. IDE sẽ hỏi "Trust project" → chọn **Trust**.
4. Đợi IDE tự động tải Gradle + Forge (lần đầu khá lâu, 10–20 phút tùy mạng, cần mạng ổn định vì tải khá nhiều dữ liệu). Theo dõi thanh tiến trình ở góc dưới phải.

## Bước 3 — Build ra file .jar

Sau khi IDE tải xong (không còn thanh tiến trình chạy):

1. Mở panel **Gradle** ở cạnh phải màn hình.
2. Vào `LagFixMod → Tasks → build → build` → double click để chạy.
   - Hoặc mở Terminal tích hợp trong IDE và gõ: `gradle build`
3. Đợi build xong (báo `BUILD SUCCESSFUL`).
4. File `.jar` sẽ nằm tại: `build/libs/lagfixmod-1.0.0.jar`

## Bước 4 — Cài vào Minecraft

1. Nếu máy chưa có Forge 1.20.1: tải Forge Installer tại https://files.minecraftforge.net (chọn version 1.20.1, bản **47.2.0** hoặc mới hơn) → chạy installer → chọn **Install client**.
2. Mở Minecraft Launcher, chọn profile "forge" vừa tạo, chạy 1 lần để Forge tạo sẵn thư mục `mods`.
3. Copy file `lagfixmod-1.0.0.jar` (ở Bước 3) vào thư mục `mods` trong thư mục `.minecraft`.
4. Mở lại game, vào **Mods** ở màn hình chính để kiểm tra mod đã xuất hiện.

---

### Lưu ý

- Đây là mod tối ưu nhẹ dựa trên API sự kiện của Forge, không thay thế hoàn toàn các mod tối ưu chuyên sâu như Embeddium/FerriteCore/ModernFix (những mod đó viết lại sâu bên trong engine bằng Mixin, phức tạp hơn nhiều). Nếu muốn hiệu quả tối đa, có thể dùng chung với các mod đó.
- Nếu build báo lỗi do khác phiên bản API (Forge đôi khi đổi tên hàm giữa các bản 47.x), gửi mình đoạn lỗi để mình sửa lại code cho khớp.
