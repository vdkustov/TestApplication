<h1>Алгоритм преобразования выражения в ОПН</h1>
<pre>
1. Если это число, то кладём его на выход.
2.Если это операция, то:
2.2 Если стек пуст или символ является открывающейся скобкой, то помещаем в стек, иначе:
2.3.1 Если последний символ в стеке, имеет меньший приоритет, чем приоритет текущего символа, то помещаем в стек.
2.3.2 Если последний символ в стеке, имеет больший либо равный приоритет, чем приоритет текущего символа, то извлекаем символы из стека пока выполняется это условие.
2.3.3 Если закрывающаяся скобка, то выталкиваем из стека все операции на выход пока не встретится открывающаяся скобка.
</pre>
<h1>Алгоритм вычисления выражения через ОПН</h1>
<pre>
1. Если в позиции число, то кладем его в стек.
2. Если в позиции операция, то применяем её к двум последним числам стека
</pre>
<br>
*ОПН - Обратная Польская Нотация
