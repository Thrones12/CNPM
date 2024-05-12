function onProvinceChange() {
    var province = document.getElementById("pSelected").value;
    fetch('/api/districts?province=' + province)
        .then(response => response.json())
        .then(data => {
            var districtSelect = document.getElementById("dSelected");
            districtSelect.innerHTML = '';
            data.forEach(district => {
                var option = document.createElement("option");
                option.text = district;
                districtSelect.add(option);
            });
            onDistrictChange();
        });
}
function onDistrictChange() {
    var province = document.getElementById("pSelected").value;
    var district = document.getElementById("dSelected").value;
    fetch('/api/wards?province=' + province + '&district=' + district)
        .then(response => response.json())
        .then(data => {
            var wardSelect = document.getElementById("wSelected");
            wardSelect.innerHTML = '';
            data.forEach(ward => {
                var option = document.createElement("option");
                option.text = ward;
                wardSelect.add(option);
            });
        });
}
function toggleDateInput() {
    var checkbox = document.getElementById("showDate");
    var dateInput = document.getElementById("dateInput");

    // Nếu checkbox được chọn, hiển thị thẻ input date, ngược lại ẩn đi
    if (checkbox.checked) {
        dateInput.style.display = "block";
    } else {
        dateInput.style.display = "none";
    }
}