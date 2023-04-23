<template>
  <div class="container">
    <div class="row">
      <div class="col-3">
        <div class="card" style="margin-top: 20px">
          <div class="card-body">
            <img :src="$store.state.user.photo" alt="" style="width: 100%" />
          </div>
        </div>
      </div>
      <div class="col-9">
        <div class="card" style="margin-top: 20px">
          <div class="card-header">
            <span style="font-size: 130%">我的Bot</span>
            <button
              type="button"
              class="btn btn-primary float-end"
              data-bs-toggle="modal"
              data-bs-target="#add-bot-btn"
            >
              创建Bot
            </button>

            <!-- Modal -->
            <div class="modal fade" id="add-bot-btn" tabindex="-1">
              <div class="modal-dialog modal-xl">
                <div class="modal-content">
                  <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">
                      创建Bot
                    </h1>
                    <button
                      type="button"
                      class="btn-close"
                      data-bs-dismiss="modal"
                      aria-label="Close"
                    ></button>
                  </div>
                  <div class="modal-body">
                    <div class="mb-3">
                      <label for="add-bot-title" class="form-label"
                        >Bot名称</label
                      >
                      <input
                        type="email"
                        class="form-control"
                        id="add-bot-title"
                        placeholder="请输入Bot名称"
                        v-model="botadd.botName"
                      />
                    </div>
                    <div class="mb-3">
                      <label for="add-bot-description" class="form-label"
                        >Bot简介</label
                      >
                      <textarea
                        class="form-control"
                        id="add-bot-description"
                        rows="3"
                        placeholder="请输入Bot简介"
                        v-model="botadd.description"
                      ></textarea>
                    </div>
                    <div class="mb-3">
                      <label for="add-bot-content" class="form-label"
                        >Bot代码</label
                      >
                      <VAceEditor
                        v-model:value="botadd.content"
                        @init="editorInit"
                        lang="c_cpp"
                        theme="textmate"
                        style="height: 300px"
                      />
                    </div>
                  </div>
                  <div class="modal-footer">
                    <div class="add-bot-error-message">
                      {{ botadd.error_message }}
                    </div>
                    <div class="add-bot-success-message">
                      {{ botadd.succes_message }}
                    </div>
                    <button
                      type="button"
                      class="btn btn-primary"
                      @click="add_bot"
                    >
                      创建
                    </button>
                    <button
                      type="button"
                      class="btn btn-secondary"
                      data-bs-dismiss="modal"
                      @click="init_botadd"
                    >
                      取消
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="card-body">
            <table class="table table-striped table-hover">
              <thead>
                <tr>
                  <th>#</th>
                  <th>bot名称</th>
                  <th>描述</th>
                  <th>创建时间</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="bot in bots" :key="bot.id">
                  <td>{{ bot.id }}</td>
                  <td>{{ bot.botName }}</td>
                  <td>{{ bot.description }}</td>
                  <td>{{ bot.createTime }}</td>
                  <td>
                    <button
                      type="button"
                      class="btn btn-secondary"
                      style="margin-right: 10px"
                      data-bs-toggle="modal"
                      :data-bs-target="'#update-bot-' + bot.id"
                    >
                      修改
                    </button>
                    <button
                      type="button"
                      class="btn btn-danger"
                      @click="remove_bot(bot.id)"
                    >
                      删除
                    </button>

                    <div
                      class="modal fade"
                      :id="'update-bot-' + bot.id"
                      tabindex="-1"
                    >
                      <div class="modal-dialog modal-xl">
                        <div class="modal-content">
                          <div class="modal-header">
                            <h1 class="modal-title fs-5">更新Bot</h1>
                            <button
                              type="button"
                              class="btn-close"
                              data-bs-dismiss="modal"
                              aria-label="Close"
                            ></button>
                          </div>
                          <div class="modal-body">
                            <div class="mb-3">
                              <label for="update-bot-title" class="form-label"
                                >Bot名称</label
                              >
                              <input
                                type="email"
                                class="form-control"
                                id="update-bot-title"
                                placeholder="请输入Bot名称"
                                v-model="bot.botName"
                              />
                            </div>
                            <div class="mb-3">
                              <label
                                for="update-bot-description"
                                class="form-label"
                                >Bot简介</label
                              >
                              <textarea
                                class="form-control"
                                id="update-bot-description"
                                rows="3"
                                placeholder="请输入Bot简介"
                                v-model="bot.description"
                              ></textarea>
                            </div>
                            <div class="mb-3">
                              <label for="update-bot-content" class="form-label"
                                >Bot代码</label
                              >
                              <VAceEditor
                                v-model:value="bot.content"
                                @init="editorInit"
                                lang="c_cpp"
                                theme="textmate"
                                style="height: 300px"
                              />
                            </div>
                          </div>
                          <div class="modal-footer">
                            <div class="update-bot-error-message">
                              {{ botupdate.error_message }}
                            </div>
                            <div class="update-bot-success-message">
                              {{ botupdate.succes_message }}
                            </div>
                            <button
                              type="button"
                              class="btn btn-primary"
                              @click="update_bot(bot)"
                            >
                              更新
                            </button>
                            <button
                              type="button"
                              class="btn btn-secondary"
                              data-bs-dismiss="modal"
                              @click="init_botupdate"
                            >
                              取消
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import { useStore } from "vuex";
import { ref, reactive } from "vue";
import { Modal } from "bootstrap/dist/js/bootstrap.min.js";
import { VAceEditor } from "vue3-ace-editor";
import ace from "ace-builds";
import 'ace-builds/src-noconflict/mode-c_cpp'

export default {
  name: "UserBotView",
  components: { VAceEditor },
  setup() {
    ace.config.set(
      "basePath",
      "https://cdn.jsdelivr.net/npm/ace-builds@" +
        require("ace-builds").version +
        "/src-noconflict/"
    );

    const store = useStore();
    const bots = ref([]);

    const botadd = reactive({
      botName: "",
      description: "",
      content: "",
      error_message: "",
      succes_message: "",
    });

    const init_botadd = () => {
      botadd.botName = "";
      botadd.description = "";
      botadd.content = "";
      botadd.error_message = "";
      botadd.succes_message = "";
    };

    const botupdate = reactive({
      error_message: "",
      succes_message: "",
    });

    const init_botupdate = () => {
      botupdate.error_message = "";
      botupdate.succes_message = "";
      get_bots();
    };

    const add_bot = () => {
      botadd.error_message = "";
      botadd.succes_message = "";
      $.ajax({
        url: "http://127.0.0.1:8088/user/bot/add/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data: {
          bot_name: botadd.botName,
          description: botadd.description,
          content: botadd.content,
        },
        success(resp) {
          console.log(resp);
          if (resp.message == "success") {
            botadd.succes_message = "创建成功";
            init_botadd();
            get_bots();
            Modal.getInstance("#add-bot-btn").hide();
          } else {
            botadd.error_message = resp.message;
          }
        },
        error(resp) {
          botadd.error_message = "连接服务器失败";
          console.log(resp);
        },
      });
    };

    const remove_bot = (bot_id) => {
      $.ajax({
        url: "http://127.0.0.1:8088/user/bot/remove/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data: {
          bot_id: bot_id,
        },
        success(resp) {
          console.log(resp);
          if (resp.message == "success") {
            alert("删除成功");
            get_bots();
          } else {
            alert(resp.message);
          }
        },
        error(resp) {
          alert("与服务器连接失败");
          console.log(resp);
        },
      });
    };

    const update_bot = (bot) => {
      botupdate.error_message = "";
      botupdate.succes_message = "";
      $.ajax({
        url: "http://127.0.0.1:8088/user/bot/update/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data: {
          bot_id: bot.id,
          bot_name: bot.botName,
          description: bot.description,
          content: bot.content,
        },
        success(resp) {
          console.log(resp);
          if (resp.message == "success") {
            init_botupdate();
            Modal.getInstance("#update-bot-" + bot.id).hide();
            alert("更新成功");
          } else {
            botupdate.error_message = resp.message;
          }
        },
        error(resp) {
          botupdate.error_message = "连接服务器失败";
          console.log(resp);
        },
      });
    };

    const get_bots = () => {
      $.ajax({
        url: "http://127.0.0.1:8088/user/bot/get_list/",
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp);
          bots.value = resp;
        },
        error(resp) {
          console.log(resp);
        },
      });
    };

    get_bots();
    return {
      get_bots,
      bots,
      botadd,
      add_bot,
      remove_bot,
      update_bot,
      botupdate,
      init_botadd,
      init_botupdate,
    };
  },
};
</script>

<style scoped>
.add-bot-error-message {
  color: red;
}

.add-bot-success-message {
  color: green;
}

.update-bot-error-message {
  color: red;
}
.update-bot-success-message {
  color: green;
}
</style>
