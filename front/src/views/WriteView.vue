<template>
  <p>안녕하세요</p>
  <p>{{count}}</p>
  <button @click="count += 1">버튼 누르면 숫자 증가</button>
</template>

<script setup lang="ts">
import {ref} from "vue";

const count = ref(0)
</script>

<style scoped>

</style>