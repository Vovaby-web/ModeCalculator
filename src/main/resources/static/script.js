function drawGraph_1(canvasId, perfData, headData_1, headData_2, title) {
    if (perfData.length === 0 || headData_1.length === 0 || headData_2.length === 0) {
        console.error("Один из массивов пустой");
        return;
    }
    const canvas = document.getElementById(canvasId);
    const ctx = canvas.getContext('2d');
    canvas.width = canvas.clientWidth;
    canvas.height = canvas.clientHeight;

    // Масштабирование
    const xScale = canvas.width / (Math.max(...perfData));
    const yScale = canvas.height / (Math.max(...headData_1, ...headData_2));

    // Рисуем оси
    ctx.beginPath();
    ctx.strokeStyle = "blue";
    ctx.moveTo(5, 0);
    ctx.lineTo(5, canvas.height);            // Ось Y
    ctx.lineTo(5, canvas.height - 5);            // Ось Y
    ctx.lineTo(canvas.width, canvas.height - 5); // Ось X
    ctx.stroke();

    // Рисуем график для headData_1
    ctx.beginPath();
    ctx.moveTo(5, canvas.height - 5 - headData_1[0] * yScale); // Начальная точка
    for (let i = 0; i < perfData.length; i++) {
        const x = 5 + perfData[i] * xScale; // Координата X
        const y = canvas.height - 5 - headData_1[i] * yScale; // Координата Y
        ctx.lineTo(x, y); // Соединяем точки
    }
    ctx.strokeStyle = "blue"; // Цвет линии графика
    ctx.stroke(); // Отображаем график

    // Рисуем график для headData_2
    ctx.beginPath();
    ctx.moveTo(5, canvas.height - 5 - headData_2[0] * yScale); // Начальная точка
    for (let i = 0; i < perfData.length; i++) {
        const x = 5 + perfData[i] * xScale; // Координата X
        const y = canvas.height - 5 - headData_2[i] * yScale; // Координата Y
        ctx.lineTo(x, y); // Соединяем точки
    }
    ctx.strokeStyle = "blue"; // Цвет линии графика
    ctx.stroke(); // Отображаем график

    // Подписи осей
    ctx.font = "16px Arial";
    ctx.textAlign = "center";
    // ctx.textBaseline = 'middle'; // Вертикальное выравнивание
    ctx.fillText(title, canvas.width / 2, 15);
    ctx.fillText("Q (м³/ч)", canvas.width - 30, canvas.height - 10);
    ctx.textAlign = "left";
    ctx.fillText("Н (м)", 10, 15);
}

function drawGraph_2(canvasId, length, headData, title) {
    const canvas = document.getElementById(canvasId);
    const ctx = canvas.getContext('2d');
    canvas.width = canvas.clientWidth;
    canvas.height = canvas.clientHeight;
    // Масштабирование
    const xScale = canvas.width / length;
    const yScale = (canvas.height - 15) / headData[0];

    // Рисуем оси
    ctx.beginPath();
    ctx.strokeStyle = "blue";
    ctx.moveTo(5, 0);
    ctx.lineTo(5, canvas.height);            // Ось Y
    ctx.lineTo(5, canvas.height - 5);            // Ось Y
    ctx.lineTo(canvas.width, canvas.height - 5); // Ось X
    ctx.stroke();

    // Рисуем график
    ctx.beginPath();
    ctx.moveTo(5, canvas.height + 5 - headData[0] * yScale); // Начальная точка
    ctx.lineTo(length * xScale, canvas.height - 5 - headData[1] * yScale); // Начальная точка

    ctx.strokeStyle = "blue"; // Цвет линии графика
    ctx.stroke(); // Отображаем график

    ctx.font = "16px Arial";
    ctx.textAlign = "center";
    ctx.fillText(title, canvas.width / 2, 15);
    ctx.fillText("L (км)", canvas.width - 25, canvas.height - 10);
    ctx.textAlign = "left";
    ctx.fillText("Н (м)", 10, 15);
}

