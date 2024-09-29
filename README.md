OwrSecRASP
OwrSecRASP é um aplicativo Android que implementa técnicas de detecção de root, usando uma combinação da biblioteca RootBeer e métodos alternativos baseados em verificações manuais. Este projeto tem como objetivo proteger a aplicação contra o uso em dispositivos com root, garantindo uma maior segurança.

Funcionalidades

- Detecção de Root com RootBeer: Usa a biblioteca open-source RootBeer para verificar se o dispositivo está com root.
- Detecção Alternativa de Root: Utiliza métodos manuais, como a verificação de tags de build, arquivos do superusuário, e binários su.
  
Tecnologias
- Kotlin: Linguagem de programação usada para a maior parte da aplicação.
- Java: Utilizada para métodos alternativos de detecção de root.
- Jetpack Compose: Framework moderno de UI para Android.
- Material 3: Biblioteca de UI para o design visual da aplicação.
- 
Como Funciona

Na inicialização do aplicativo, duas classes de detecção de root são instanciadas:

1. rootDetector - Usa a biblioteca RootBeer para verificar se o dispositivo está com root.
2. rootDetector2 - Implementa uma série de verificações manuais para detectar se o dispositivo tem root.
Se qualquer uma dessas detecções identificar que o dispositivo tem root, o aplicativo é finalizado para prevenir seu uso.

Código de Detecção de Root
RootBeer (Kotlin):
``` 
class rootDetector {
    fun checkRoot(context: Context): Boolean {
        val rootBeer = RootBeer(context)
        return rootBeer.isRooted()
    }
}
   ```
Verificações Manuais (Java):
```
public class rootDetector2 {
    public boolean isDeviceRooted() {
        return checkBuildTags() || checkSuperUserPaths() || checkSuBinary();
    }

    private static boolean checkBuildTags() {
        return "test-keys".equals(android.os.Build.TAGS);
    }

    private static boolean checkSuperUserPaths() {
        List<String> paths = Arrays.asList(
            "/system/app/Superuser.apk",
            // outros caminhos...
        );
        for (String path : paths) {
            if (new File(path).exists()) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkSuBinary() {
        String[] commands = {"/system/xbin/which su", "/system/bin/which su", "which su"};
        try {
            for (String command : commands) {
                Process process = Runtime.getRuntime().exec(command);
                int exitValue = process.waitFor();
                if (exitValue == 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
``` 
Requisitos
- Android Studio Arctic Fox (ou mais recente)
- Kotlin 1.5.0 (ou mais recente)
- Java 1.8 (ou mais recente)
Como Usar

1. Clone o repositório:
```
git clone https://github.com/seu-usuario/OwrSecRASP.git
```
2. Importe o projeto no Android Studio.

3. Compile e execute o projeto em um dispositivo Android ou emulador.

4. O aplicativo irá detectar se o dispositivo tem root. Se for detectado, o app será encerrado automaticamente.
Estrutura do Projeto
- MainActivity.kt: Contém a lógica principal da aplicação e integra as detecções de root.
- rootDetector.kt: Implementa a detecção de root usando a biblioteca RootBeer.
- rootDetector2.java: Implementa métodos alternativos de detecção de root baseados em verificações manuais.

Personalização
Se você deseja adicionar mais verificações ou modificar o comportamento ao detectar root, você pode editar as classes rootDetector ou rootDetector2 conforme necessário.

Referências

- RootBeer
- Jetpack Compose

Licença
Este projeto está licenciado sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

