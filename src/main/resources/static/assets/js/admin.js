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
