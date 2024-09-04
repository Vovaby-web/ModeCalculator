function drawGraph_1(canvasId, perfData, headData_1, headData_2, perfCur, headCur, title) {
    if (perfData.length === 0 || headData_1.length === 0 || headData_2.length === 0) {
        console.error("Один из массивов пустой");
        return;
    }
    const canvas = document.getElementById(canvasId);
    const ctx = canvas.getContext('2d');
    canvas.width = canvas.clientWidth;
    canvas.height = canvas.clientHeight;

    const movx = 35;
    const movy = 30;
    // Масштабирование
    const xScale = (canvas.width - movx) / (Math.max(...perfData));
    const yScale = (canvas.height - movy) / (Math.max(...headData_1, ...headData_2));

    // Рисуем оси
    ctx.beginPath();
    ctx.lineWidth = 2 // Устанавливаем толщину линии
    ctx.strokeStyle = "black";
    ctx.moveTo(movx, 0);
    ctx.lineTo(movx, canvas.height);            // Ось Y
    ctx.moveTo(movx, canvas.height - movy);
    ctx.lineTo(canvas.width, canvas.height - movy); // Ось X
    ctx.stroke();

    // Рисуем текущие значения Q и H
    ctx.beginPath();
    ctx.lineWidth = 1 // Устанавливаем толщину линии
    ctx.strokeStyle = "red";
    ctx.moveTo(movx, canvas.height - movy - headCur * yScale);
    ctx.lineTo(movx + perfCur * xScale, canvas.height - movy - headCur * yScale);            // Ось Y
    ctx.lineTo(movx + perfCur * xScale, canvas.height - movy);
    ctx.stroke();

    // Рисуем график для headData_1
    ctx.beginPath();
    ctx.moveTo(movx, canvas.height - movy - headData_1[0] * yScale); // Начальная точка
    for (let i = 0; i < perfData.length; i++) {
        const x = movx + perfData[i] * xScale; // Координата X
        const y = canvas.height - movy - headData_1[i] * yScale; // Координата Y
        ctx.lineTo(x, y); // Соединяем точки
    }
    ctx.strokeStyle = "green"; // Цвет линии графика
    ctx.lineWidth = 3 // Устанавливаем толщину линии
    ctx.stroke(); // Отображаем график

    // Рисуем график для headData_2
    ctx.beginPath();
    ctx.moveTo(movx, canvas.height - movy - headData_2[0] * yScale); // Начальная точка
    for (let i = 0; i < perfData.length; i++) {
        const x = movx + perfData[i] * xScale; // Координата X
        const y = canvas.height - movy - headData_2[i] * yScale; // Координата Y
        ctx.lineTo(x, y); // Соединяем точки
    }
    ctx.strokeStyle = "blue"; // Цвет линии графика
    ctx.lineWidth = 3 // Устанавливаем толщину линии
    ctx.stroke(); // Отображаем график

    // Подписи осей
    ctx.lineWidth = 1 // Устанавливаем толщину линии
    ctx.font = "17px Times New Roman";
    ctx.textAlign = "center";
    // ctx.textBaseline = 'middle'; // Вертикальное выравнивание
    ctx.fillText(title, canvas.width / 2, 15);
    ctx.font = "14px Times New Roman";
    ctx.fillText("Q(м³/ч)", canvas.width - 30, canvas.height - movy / 2);
    ctx.textAlign = "left";
    ctx.fillText("Н(м)", 3, 15);
    ctx.fillText("0", movx - 10, canvas.height - movy - 5);
    // Установка цвета текста
    ctx.fillStyle = "red"; // Цвет в любом формате: "red", "#FF0000", "rgb(255, 0, 0)"
    ctx.fillText(headData_1[0], movx - 25, canvas.height - movy - headData_1[0] * yScale);
    ctx.fillText(headData_2[0], movx - 25, canvas.height - movy - headData_2[0] * yScale);
    ctx.fillText(headCur, movx + 5, canvas.height - movy - headCur * yScale - 5);
    ctx.fillText(perfCur, movx + perfCur * xScale + 2, canvas.height - movy - 5);

    // Рисуем точку
    ctx.beginPath();
    ctx.fillStyle = "red";
    // Рисуем окружность от 0 до 2π радианов
    ctx.arc(movx, canvas.height - movy - headCur * yScale,
        4, 0, Math.PI * 2);
    ctx.fill();
    ctx.beginPath();
    // Рисуем окружность от 0 до 2π радианов
    ctx.arc(movx + perfCur * xScale, canvas.height - movy - headCur * yScale,
        4, 0, Math.PI * 2);
    ctx.fill();
    ctx.beginPath();
    // Рисуем окружность от 0 до 2π радианов
    ctx.arc(movx + perfCur * xScale, canvas.height - movy,
        4, 0, Math.PI * 2);
    // Закрашиваем окружность
    ctx.fill();
    ctx.beginPath();
    ctx.fillStyle = "black";
    // Рисуем окружность от 0 до 2π радианов
    ctx.arc(movx, canvas.height - movy,
        4, 0, Math.PI * 2);
    // Закрашиваем окружность
    ctx.fill();
}

function drawGraph_2(canvasId, length, headData, iforq, title) {
    const canvas = document.getElementById(canvasId);
    const ctx = canvas.getContext('2d');
    canvas.width = canvas.clientWidth;
    canvas.height = canvas.clientHeight;
    const movx = 35;
    const movy = 30;
    // Масштабирование
    const xScale = (canvas.width - movx - 20) / length;
    const yScale = (canvas.height - movy - 30) / headData[0];

    // Рисуем оси
    ctx.beginPath();
    ctx.lineWidth = 2 // Устанавливаем толщину линии
    ctx.strokeStyle = "black";
    ctx.moveTo(movx, 0);
    ctx.lineTo(movx, canvas.height);            // Ось Y
    ctx.moveTo(movx, canvas.height - movy);
    ctx.lineTo(canvas.width, canvas.height - movy); // Ось X
    ctx.stroke();

    // Рисуем график
    ctx.beginPath();
    ctx.lineWidth = 3 // Устанавливаем толщину линии
    // Начальная точка
    ctx.moveTo(movx, canvas.height - movy - headData[0] * yScale); // Начальная точка
    // Рисуем линию
    ctx.lineTo(length * xScale + movx + 20, canvas.height - movy - headData[1] * yScale);
    // Цвет линии графика
    ctx.strokeStyle = "blue";
    // Отображаем график
    ctx.stroke();

    // Проекции на ось х
    ctx.beginPath();
    ctx.lineWidth = 1 // Устанавливаем толщину линии
    ctx.strokeStyle = "red";
    ctx.moveTo(movx+2, canvas.height - movy - headData[0] * yScale);
    ctx.lineTo(movx+2, canvas.height - movy);
    ctx.moveTo(length * xScale + movx + 20-2, canvas.height - movy - headData[1] * yScale);
    ctx.lineTo(length * xScale + movx + 20-2, canvas.height - movy);
    ctx.moveTo(length * xScale + movx + 20-2, canvas.height - movy - headData[1] * yScale);
    ctx.lineTo(movx+2, canvas.height - movy - headData[1] * yScale);
    ctx.stroke();

    // Подписи осей
    ctx.lineWidth = 1 // Устанавливаем толщину линии
    ctx.font = "17px Times New Roman";
    ctx.textAlign = "center";
    // ctx.textBaseline = 'middle'; // Вертикальное выравнивание
    ctx.fillText(title, canvas.width / 2, 15);
    ctx.font = "14px Times New Roman";
    ctx.fillText("L(км)", canvas.width - 30, canvas.height - movy / 2);
    ctx.textAlign = "left";
    ctx.fillText("Н(м)", 3, 15);
    ctx.fillText("0", movx - 10, canvas.height - movy - 5);
    // Установка цвета текста
    ctx.fillStyle = "red"; // Цвет в любом формате: "red", "#FF0000", "rgb(255, 0, 0)"
    ctx.fillText(headData[0], movx - 25, canvas.height - movy - headData[0] * yScale);
    ctx.fillText(length, movx + length * xScale - 10, canvas.height - movy - 5);
    ctx.fillText(headData[1], movx - 25, canvas.height - movy - headData[1] * yScale);

    ctx.font = "16px Times New Roman";
    // iforq.toFixed(6) - сокращаем число до 6 знаков после запятой
    ctx.fillText("i(Q) = "+iforq.toFixed(6), ((length * xScale + movx + 20)-movx)/2,
        ((canvas.height - movy - headData[1] * yScale)-(canvas.height - movy - headData[0] * yScale))/2);

    ctx.beginPath();
    ctx.fillStyle = "black";
    // Рисуем окружность от 0 до 2π радианов
    ctx.arc(movx, canvas.height - movy,
        4, 0, Math.PI * 2);
    // Закрашиваем окружность
    ctx.fill();
    ctx.beginPath();
    ctx.fillStyle = "red";
    // Рисуем окружность от 0 до 2π радианов
    ctx.arc(length * xScale + movx + 20-2, canvas.height - movy - headData[1] * yScale,
        4, 0, Math.PI * 2);
    // Закрашиваем окружность
    ctx.fill();
    ctx.beginPath();
    ctx.fillStyle = "red";
    // Рисуем окружность от 0 до 2π радианов
    ctx.arc(movx+2, canvas.height - movy - headData[0] * yScale,
        4, 0, Math.PI * 2);
    // Закрашиваем окружность
    ctx.fill();
    ctx.beginPath();
    ctx.fillStyle = "red";
    // Рисуем окружность от 0 до 2π радианов
    ctx.arc(movx+2, canvas.height - movy - headData[1] * yScale,
        4, 0, Math.PI * 2);
    // Закрашиваем окружность
    ctx.fill();
}

