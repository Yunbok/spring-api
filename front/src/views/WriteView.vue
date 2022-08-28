<template>
  <div>
    <el-input type="text" v-model="title" placeholder="제목을 입력해주세요"/>
  </div>

  <div class="mt-2">
    <el-input type="textarea" v-model="content" rows="15" />
  </div>

  <div class="mt-2">
    <el-button type="primary" @click="write">글 작성 완료</el-button>
  </div>

</template>

<script setup lang="ts">
import {ref} from "vue";
import axios from "axios";

const title = ref("");
const content = ref("");

const write = function () {
  axios.get("http://google.com")
}
</script>

<style scoped>

</style>