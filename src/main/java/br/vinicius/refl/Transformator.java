// Generic Types: https://docs.oracle.com/javase/tutorial/java/generics/types.html
package br.vinicius.refl;

// A classe Transformator foi criada para tentar "transformar" um objeto
// em outro tipo de objeto, possivelmente sua versão DTO (Data Transfer Object).
public class Transformator {

    // O método transform é genérico: <I, O> significa que ele pode receber
    // um tipo de entrada (I) e retornar um tipo de saída (O).
    // No entanto, neste código ainda não há retorno implementado.
    public <I, O> transform(I input) throws ClassNotFoundException {

        // Aqui obtemos a classe do objeto de entrada.
        // Exemplo: se input for do tipo Produto, source será Produto.class.
        Class<?> source = input.getClass();

        // A ideia é procurar dinamicamente a classe correspondente ao DTO.
        // Ele tenta carregar uma classe cujo nome seja o mesmo da classe original
        // acrescido de "DTO". Exemplo: Produto -> ProdutoDTO.
        Class<?> target = source.forName(source.getClass() + "DTO");

        // Neste ponto, o código não faz nada com 'target'.
    }
}
