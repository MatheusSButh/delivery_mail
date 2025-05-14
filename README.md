<body>
    <h1>🛍 Sistema de pedidos com Spring Boot, JPA e integrações externas (ViaCep, Melhor Envio)</h1>
    <p>Este repositório contém um projeto backend desenvolvido com Spring Boot para gerenciar pedidos de compra. O sistema integra-se com APIs externas para automatizar a obtenção de dados de endereço (ViaCep) e o cálculo de frete e tempo de entrega (Melhor Envio).</p>
    <h2>🚀 Tecnologias Utilizadas</h2>
    <ul>
        <li>Java 21</li>
        <li>Spring Boot</li>
        <li>Spring Data JPA / Hibernate</li>
        <li>Banco de dados relacional (PostgreSQL)</li>
        <li>Maven</li>
        <li>Lombok</li>
        <li>RestTemplate</li>
        <li>Gson</li>
        <li>ViaCep API</li>
        <li>Melhor Envio API</li>
        <li>Mockito e JUnit para testes básicos</li>
    </ul>
    <h2>📌 Funcionalidades</h2>
    <ul>
        <li><strong>Gerenciamento de itens:</strong> Operações CRUD (Create, Read, Update, Delete) para produtos.</li>
        <li><strong>Gerenciamento de usuários:</strong> Operações CRUD para usuários.
            <ul>
                <li>Busca automática de endereço completo via <strong>ViaCep</strong> ao cadastrar/atualizar usuário pelo CEP.</li>
                <li>Reutilização de endereços já cadastrados no banco de dados.</li>
            </ul>
        </li>
        <li><strong>Gerenciamento de pedidos:</strong> Operações CRUD para pedidos de compra.
            <ul>
                <li>Associação de múltiplos itens a um pedido.</li>
                <li>Definição de usuário remetente e destinatário.</li>
                <li>Cálculo automático de frete e tempo estimado de entrega via <strong>Melhor Envio</strong> com base nos CEPs do remetente e destinatário.</li>
                <li>Cálculo do valor total do pedido (soma dos itens + valor do frete).</li>
            </ul>
        </li>
        <li>Tratamento de exceções personalizadas para erros como busca inválida, CEP inválido ou falha no cálculo de frete.</li>
    </ul>
    <h2>🛠 Configuração e Execução</h2>
    <ol>
        <li>
            <p><strong>Clone o repositório:</strong></p>
            <pre><code>git clone https://github.com/MatheusSButh/delivery_mail.git</code></pre>
        </li>
        <li>
            <p><strong>Configure as variáveis de ambiente:</strong><br>
            Crie um arquivo .env na raiz do projeto. Em seguida, insira as seguintes informações:</p>
            <pre><code class="language-properties">DB_URL=(SEU_BANCO://SEU_HOST:SEU_PORTA/SEU_DB)
DB_PASSWORD=(Sua senha)
DB_USERNAME=(Seu usuário)
MELHOR_ENVIO_URL=https://sandbox.melhorenvio.com.br/api/v2/me/shipment/calculate
MELHOR_ENVIO_TOKEN=(Seu Token da API MelhorEnvio - Para gerar o token é necessário se cadastrar no sandbox da melhor envio e gerar um token no painel: https://melhorenvio.com.br/login)
MELHOR_ENVIO_USER_AGENT=(Seu email)
VIA_CEP_URL=https://viacep.com.br/ws/
</code></pre>
        </li>
        <li>
            <p><strong>Execute o projeto:</strong><br>
            Abra o terminal na raiz do projeto e execute o comando Maven:</p>
            <pre><code>mvn spring-boot:run
</code></pre>
        </li>
    </ol>
    <h2>📝 Autor</h2>
    <p>Matheus de Souza Buth - <a href="https://www.linkedin.com/in/matheusbuth/">https://www.linkedin.com/in/matheusbuth/</a></p>
    <hr>
</body>
</html>
