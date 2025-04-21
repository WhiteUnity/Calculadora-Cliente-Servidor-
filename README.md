# 🧮 Calculadora Cliente-Servidor en Java

**Autor:** Joseff Antonio Laverde Avendaño  
**Materia:** Programación III 

**Universidad:** $Universidad Pedagógica y Tecnológica de Colombia (UPTC) 
**Profesor:** Jorge Hoyos 


---

## 📋 Descripción del Proyecto
Implementación de una calculadora distribuida que opera bajo una arquitectura cliente-servidor utilizando sockets TCP/IP. El sistema permite realizar operaciones básicas (suma, resta, multiplicación y división) entre números reales, con validación de entradas y manejo de errores.

---

## 🛠️ Características Principales
- ✅ Arquitectura cliente-servidor multi-hilos
- ✅ Operaciones soportadas: `sumar`, `restar`, `multiplicar`, `dividir`
- ✅ Validación de entradas en cliente y servidor
- ✅ Manejo de errores robusto (división por cero, formatos inválidos)
- ✅ Soporte para números decimales
- ✅ Conexiones persistentes y concurrentes

---

## ⚙️ Prerrequisitos
- **Java JDK 8+** ([Descargar](https://www.oracle.com/java/technologies/downloads/))
- **IDE Java** (Eclipse/IntelliJ) o **CLI**
- **Git** ([Guía de instalación](https://git-scm.com/book/es/v2/Inicio---Sobre-el-Control-de-Versiones-Instalación-de-Git))

---

## 🚀 Instalación y Configuración

### 1. Clonar repositorio
```bash
git clone https://github.com/tu-usuario/calculadora-cliente-servidor.git
cd calculadora-cliente-servidor
```

### 2. Compilar proyecto
```bash
javac -d bin src/main/java/com/calculator/**/*.java
```

---

## 🖥️ Ejecución del Sistema

### Iniciar Servidor
```bash
java -cp bin com.calculator.server.CalculatorServer
```

### Iniciar Cliente (en terminal separada)
```bash
java -cp bin com.calculator.client.CalculatorClient
```

---

## 🧩 Modo de Uso
1. **Formato de entrada:**  
   `operación número1 número2`  
   Ejemplo: `dividir 15.5 3.2`

2. **Operaciones válidas:**
   ```
   sumar      → suma aritmética
   restar     → resta aritmética
   multiplicar → producto
   dividir    → división real
   ```

3. **Salir del cliente:**  
   Escribir `salir` en cualquier momento

---

## 📂 Estructura del Proyecto
```
src/
├── main/
│   └── java/
│       └── com/
│           └── calculator/
│               ├── client/          # Código cliente
│               ├── server/          # Lógica del servidor
│               └── common/          # Recursos compartidos
```

---

## 🧪 Ejemplos de Prueba
| Entrada              | Salida Esperada |
|----------------------|-----------------|
| `sumar 5 3.2`        | `8.20`          |
| `dividir 10 0`       | `Error: División por cero` |
| `multiplicar 7 hola` | `Error: Valores numéricos inválidos` |

---

## 👥 Contribuidores
- **Joseff Antonio Laverde Avendaño**  
  Estudiante de Ingeniería de Sistemas  
  Contacto: [joseff.laverde01@uptc.edu.co](mailto:joseff.laverde01@uptc.edu.co)

---

## 🙏 Agradecimientos
A la **Universidad Pedagógica y Tecnológica de Colombia** y al docente de Programación III por el acompañamiento en el desarrollo de este proyecto.


