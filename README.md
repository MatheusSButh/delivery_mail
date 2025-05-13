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
            <p><strong>Configure o banco de dados:</strong><br>
            Atualize o arquivo <code>src/main/resources/application.properties</code>com as credenciais e URL do seu banco de dados relacional.</p>
            <pre><code class="language-properties">spring.datasource.url=jdbc:SEU_BANCO://SEU_HOST:SEU_PORTA/SEU_DB
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
</code></pre>
        </li>
        <li>
            <p><strong>Configure o Melhor Envio API:</strong><br>
            No arquivo <code>com.buthdev.demo.services.MelhorEnvioService.java</code>, substitua os campos pelas suas credenciais do Melhor Envio (Para gerar o token é necessário se cadastrar no sandbox da melhor envio e gerar um token no painel: https://melhorenvio.com.br/login</p>
            <pre><code class="language-java">private static final String TOKEN = "SEU TOKEN";
private static final String USER_AGENT = "Aplicação (SEU EMAIL)";
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
